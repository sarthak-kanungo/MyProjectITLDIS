package com.i4o.dms.kubota.security.jwt;

import com.i4o.dms.kubota.security.model.AuthenticatedUser;
import com.i4o.dms.kubota.security.model.SecurityUser;
import com.i4o.dms.kubota.utils.JweKeyStoreUtil;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;


@Component
public class JwtProvider {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;

    private final String AUDIENCE_MOBILE = "mobile";
    private final String AUDIENCE_TABLET = "tablet";

    @Value("${service.dms.jwtSecret}")
    private String secret;

    @Value("${service.dms.jwtExpiration}")
    private Long expiration;
	
	@Value("${jwe.keystore.path}")
    private String keyStorePath;

    @Value("${jwe.keystore.password}")
    private String keyStorePassword;

    @Value("${jwe.keystore.alias}")
    private String alias;

    @Value("${jwe.keystore.keyPassword}")
    private String keyPassword;

    /**
     * @author suraj.gaur
     * @implNote Implemented on 07-10-2024 on request of client for resolving VAPT points.
     * @throws Exception
     */
    @PostConstruct
    public void initKeys() throws Exception {
        this.privateKey = JweKeyStoreUtil.loadPrivateKeyFromKeyStore(keyStorePath, keyStorePassword, alias, keyPassword);
        this.publicKey = JweKeyStoreUtil.loadPublicKeyFromKeyStore(keyStorePath, keyStorePassword, alias);
    }
    
    public String getUsername(String token) {
        final Claims claims = this.getClaimsFromToken(token);
        logger.info("claims:"+claims);
        logger.info("claims.getSubject:"+claims);
        return claims.getSubject();
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) claims.get("created"));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            audience = (String) claims.get("audience");
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = this.getAudienceFromToken(token);
        return (this.AUDIENCE_TABLET.equals(audience) || this.AUDIENCE_MOBILE.equals(audience));
    }

    public String generateToken(AuthenticatedUser userDetails) {//}, Device device) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("status", userDetails.getLoginIdStatus());
        claims.put("dealerEmployeeId",userDetails.getDealerEmployeeId());
        claims.put("kubotaEmployeeId",userDetails.getKubotaEmployeeId());
        claims.put("dealerId",userDetails.getDealerId());
        claims.put("id",userDetails.getId());
        claims.put("branchId", userDetails.getBranchId());
        //claims.put("audience", this.generateAudience(device));
        claims.put("created", this.generateCurrentDate());
        return this.generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = this.getCreatedDateFromToken(token);
        return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset)) && (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("created", this.generateCurrentDate());
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        SecurityUser user = (SecurityUser) userDetails;
        final String username = this.getUsernameFromToken(token);
//        final Date created = this.getCreatedDateFromToken(token);
//        final Date expiration = this.getExpirationDateFromToken(token);
        return (username.equals(user.getUsername()) && !(this.isTokenExpired(token)));// && (user.getEnabled())
    }
    
    /**
     * @author suraj.gaur
     * @implNote Implemented on 04-10-2024 on request of client for resolving VAPT points.
     * @param userDetails
     * @return String
     * @throws NoSuchAlgorithmException, JOSEException
     */
    public String generateEncryptedToken(AuthenticatedUser userDetails) throws NoSuchAlgorithmException, JOSEException {
    	return this.encryptJWT(generateToken(userDetails));
    }

    /**
     * @author suraj.gaur
     * @implNote Implemented on 04-10-2024 on request of client for resolving VAPT points.
     * @param token
     * @return String
     * @throws NoSuchAlgorithmException, JOSEException 
     */
    public String encryptJWT(String token) throws NoSuchAlgorithmException, JOSEException {
	    // Creating the JWE header with RSA-OAEP encryption and AES-GCM
        JWEHeader header = new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM)
                .contentType("JWT")  // Optional
                .build();
        
        JWEObject jweObject = new JWEObject(header, new Payload(token));
        jweObject.encrypt(new RSAEncrypter(this.publicKey));

        // Serializing to compact form for encrypted token
        String encryptedToken = jweObject.serialize(); 
        return encryptedToken;
	}
    
    /**
     * @author suraj.gaur
     * @implNote Implemented on 04-10-2024 on request of client for resolving VAPT points.
     * @param encryptedToken
     * @return String
     * @throws Exception
     */
    public String decryptToken(String encryptedToken) throws Exception {
        JWEObject jweObject = JWEObject.parse(encryptedToken);
        jweObject.decrypt(new RSADecrypter(privateKey));
        return jweObject.getPayload().toString();
    }
}
