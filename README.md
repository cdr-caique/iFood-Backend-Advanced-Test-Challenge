# iFood Backend Advanced Test

Create a micro-service able to accept RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature.

## Business rules

* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is between 15 and 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks

## Hints

You can make usage of OpenWeatherMaps API (https://openweathermap.org) to fetch temperature data and Spotify (https://developer.spotify.com) to suggest the tracks as part of the playlist.

## Non functional requirements

As this service will be a worldwide success, it must be prepared to be fault tolerant, responsive and resilient.

Use whatever language, tools and frameworks you feel comfortable to, and briefly elaborate on your solution, architecture details, choice of patterns and frameworks.

Also, make it easy to deploy/run your service(s) locally (consider using some container/vm solution for this). Once done, share your code with us.

## Solution

For running the project, it is necessary, first, to set three environment variables:

- SPOTIFY_CLIENT_ID
- SPOTIFY_CLIENT_SECRET
- OPEN_WEATHER_APP_ID

For generating your Spotify variables:

- Browse to https://developer.spotify.com/dashboard/applications.
- Log in with your Spotify account.
- Click on ‘Create an app’.
- Pick an ‘App name’ and ‘App description’ of your choice and mark the checkboxes.
- After creation, you see your ‘Client Id’ and you can click on ‘Show client secret` to unhide your ’Client secret’.

For generating the OpenWeather variable:

- Browse to https://home.openweathermap.org/api_keys
- Create an account, or log in.

With all variables seted, you can run the project. After running, browse to API documentation:

- localhost:8080/swagger-ui

It is necessary to authenticate. The default user and password are both "user".

The documentation shows all the endpoints and theirs uses.