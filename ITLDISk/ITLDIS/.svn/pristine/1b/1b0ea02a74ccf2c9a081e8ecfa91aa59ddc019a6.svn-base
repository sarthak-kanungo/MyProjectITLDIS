(function () {
  const contextPath = window.appContextPath || '';
  //alert("contextPath1:: " + contextPath);

  const pageKey = `active_${contextPath}_${window.location.pathname}${window.location.search}`;
  //alert("pageKey1:: " + pageKey);

  const HEARTBEAT_INTERVAL = 3000;
  const EXPIRY_TIME = 7000;

  function sendHeartbeat() {
    localStorage.setItem(pageKey, Date.now().toString());
  }

  const lastSeen = parseInt(localStorage.getItem(pageKey), 10);
  const now = Date.now();

  if (lastSeen && now - lastSeen < EXPIRY_TIME) {
    alert("This page is already open in another tab!");
    window.location.href = "about:blank";
    return;
  }

  sendHeartbeat();
  const heartbeatTimer = setInterval(sendHeartbeat, HEARTBEAT_INTERVAL);

  window.addEventListener("beforeunload", () => {
    clearInterval(heartbeatTimer);
    localStorage.removeItem(pageKey);
  });
})();
