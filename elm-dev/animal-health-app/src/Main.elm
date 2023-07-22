module Main exposing (..)

import Browser
import Html exposing (Html, div, text, button)
import Html.Events exposing (onClick)
import Http exposing (Error, expectJson, get)
import Json.Decode exposing (Decoder, int, string)

-- Model
type alias Model =
    { message : String
    , animalTag : String
    }

init : Model
init =
    { message = ""
    , animalTag = ""
    }

-- Msg
type Msg
    = UpdateAnimalTag String
    | FetchNextVisitDate
    | SetNextVisitDate (Result Error String)

-- Update
update : Msg -> Model -> Model
update msg model =
    case msg of
        UpdateAnimalTag tag ->
            { model | animalTag = tag }

        FetchNextVisitDate ->
            ( model, getVisitDate model.animalTag SetNextVisitDate )

        SetNextVisitDate (Ok date) ->
            { model | message = "Next visit date: " ++ date }

        SetNextVisitDate (Err error) ->
            { model | message = "Error fetching data: " ++ string error }
a
-- View
view : Model -> Html Msg
view model =
    div []
        [ div []
            [ button [ onClick FetchNextVisitDate ] [ text "Compute Next Visit Date" ]
            , div [] [ text model.message ]
            ]
        ]

-- Http Request
getVisitDate : String -> (Result Error String -> msg) -> Cmd msg
getVisitDate animalTag msg =
    let
        url =
            "https://api.example.com/animal-health/" ++ animalTag
    in
    Http.get
        { url = url
        , expect = Http.expectJson (Decode.map SetNextVisitDate (Decode.field "nextVisitDate" string))
        }

-- Browser
main =
    Browser.sandbox { init = init, update = update, view = view }

