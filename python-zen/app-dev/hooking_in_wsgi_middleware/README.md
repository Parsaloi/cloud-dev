# wsgi-app

Describe your project here...

## Hooking in WSGI Middleware

To add WSGI middleware to your Flask application, wrap the application's `wsgi_app` attribute. For example, to apply Werkzeug's `ProxyFix` middleware  
<https://werkzeug.palletsprojects.com/en/2.3.x/middleware/proxy_fix/#werkzeug.middleware.proxy_fix.ProxyFix> for running behind `Nginx` see code...
