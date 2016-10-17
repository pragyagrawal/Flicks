# Flicks

**Flicks** is an android app that displays list of movies from "TheMoviesdatabase" API.

Time spent: **12-15** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ] User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.
* [ ] User can view in both Landscape and potrait mode.

The following **optional** features are implemented:

* [ ] User can **pull-to-refresh** popular stream to get the latest movies.
* [ ] Display a nice default [placeholder graphic](http://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#configuring-picasso) for each image during loading.

The following **bonus** features are implemented:

* [ ] Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [ ] When viewing a popular movie (i.e. a movie voted for more than 5 stars) the video should show the full backdrop image as the layout.  Uses [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) or [Heterogenous RecyclerView](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) to show different layouts.
* [ ] Allow video trailers to be played in full-screen using the YouTubePlayerView.
    * [ ] Overlay a play icon for videos that can be played.
    * [ ] More popular movies should start a separate activity that plays the video immediately.
    * [ ] Less popular videos rely on the detail page should show ratings and a YouTube preview.
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* [ ] Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)
* [ ] Replaced android-async-http network client with the popular [OkHttp](http://guides.codepath.com/android/Using-OkHttp) or [Volley](http://guides.codepath.com/android/Networking-with-the-Volley-Library) networking libraries.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [Butterknife] (com.jakewharton:butterknife:8.2.1') - Reduce view binding boilerplate
- [OkHTTP] (com.squareup.okhttp3:logging-interceptor:3.4.1) - Logs the network connection and API request and response
- [Retrofit] (com.squareup.retrofit2:retrofit:2.1.0' and 'com.squareup.retrofit2:converter-gson:2.1.0') - Retrofit for the json parsing

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
