{-# LANGUAGE OverloadedStrings	#-}
{-# LANGUAGE QuasiQuotes	#-}
{-# LANGUAGE TemplateHaskell	#-}
{-# LANGUAGE TypeFamilies	#-}
import Yesod

data App = App
mkYesod "App" [parseRoutes|
/ HomeR GET
|]
instance Yesod App

getHomeR = defaultLayout $ do
	setTitle "Widget Web App"
	toWidget [lucius| h1 { color: crimson; }|]
	addScriptRemote "https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"
	toWidget
		[julius|
			$(funtion() {
				$("h1").click(funtion(){
					alert("You clicked on the heading!");
				});
			});
		|]
	toWidgetHead
		[hamlet|
			<meta name=keywords content="some sample keywords">
		|]
	toWidget
		[hamlet|
			<h1>Here's one way of including content
		|]
	[whamlet|<h2>Here's another |]
	toWidgetBody
		[julius|
			alert{"This is included in the body itself"};
		|]
main = warp 3002 Ap
