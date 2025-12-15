# Quick Deployment Guide

## 🚀 One-Minute Deployment

### 1. Build (Already Done ✅)
```bash
cd ITLDIS
mvn clean package -DskipTests
```
**Status**: ✅ Complete - WAR file created at `target/itldis.war`

---

### 2. Deploy

#### Tomcat:
```bash
# Copy WAR to Tomcat
cp target/itldis.war $CATALINA_HOME/webapps/

# Start Tomcat
$CATALINA_HOME/bin/startup.sh

# Check logs
tail -f $CATALINA_HOME/logs/catalina.out
```

#### WebLogic:
1. Open Console: `http://localhost:7001/console`
2. Deployments → Install → Select `target/itldis.war`
3. Start Application

#### JBoss/WildFly:
```bash
cp target/itldis.war $JBOSS_HOME/standalone/deployments/
$JBOSS_HOME/bin/standalone.sh
```

---

### 3. Test

**Open Browser**:
```
http://localhost:8080/itldis/camunda/test.jsp
```

**Quick Test**:
1. Start Process: `SampleProcess` with User ID: `demo`
2. Get Tasks: User ID: `demo`
3. Complete Task: Use Task ID from step 2

---

## ✅ Success Indicators

**Check Logs For**:
```
[INFO] Camunda ProcessEngine initialized: default
[INFO] Deployed Camunda process: processes/sample-process.bpmn
```

**Test Page Should**:
- Load without errors
- Show all operation forms
- Allow starting processes

---

## 🆘 Quick Troubleshooting

**No ProcessEngine logs?**
- Check `web.xml` has listeners
- Verify `camunda.properties` exists

**Database errors?**
- H2: No setup needed (auto-creates)
- SQL Server: Create database and update `camunda.properties`

**404 on test page?**
- Verify WAR deployed correctly
- Check application context path

---

**Ready to Deploy!** 🎉
