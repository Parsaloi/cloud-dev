from werkzeug.middleware.proxy_fix import ProxyFix
app.wsgi_app = ProxyFix(app.wsgi_app)

# Wrapping 'app.wsgi_app' instead of 'app' means that 'app' still points at your Flask application, not at the middleware, so you can continue to  
# use and configure 'app' directly
