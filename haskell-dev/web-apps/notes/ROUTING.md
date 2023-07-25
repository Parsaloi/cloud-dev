
## Routing
> <https://raw.githubusercontent.com/yesodweb/yesodweb.com/master/LICENSE>

Like most modern web frameworks, `Yesod` follows a **front controller pattern**. This means that every request to a Yesod application enters at the same  
and is routed from there. As a contrast, in systems like PHP and ASP you usually create a number of different files, and the web server automatically  
directs requests to the relevant file

In addition, Yesod uses a declarative style for specifying routes. In our example above, this looked like  
```haskell
mkYesod "HelloWorld" [parseRoutes|
/ HomeR GET
|]
```
> `mkYesod` is a Template Haskell funtion, and `parseRoutes` is a QuasiQuoter

All this means is: We create one route in our web application say `HomeR`, it should listen for request to `/` (the root os the application),  
and should answer HTTP `GET` requests. `HomeR` is a resource
