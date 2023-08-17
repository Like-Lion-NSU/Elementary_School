const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: "http:27.96.131.94/:8081",
      changeOrigin: true,
    })
  );
};
