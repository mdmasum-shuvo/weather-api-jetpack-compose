
<h2>🌦 Weather App Features</h2>

<h3>1. Weather Based on Current Location</h3>
<ul>
  <li>Automatically detects the user's location using device GPS.</li>
  <li>Fetches real-time weather data for the current location.</li>
  <li>Displays:
    <ul>
      <li>Temperature</li>
      <li>Location</li>
       <li>Icon</li>
      <li>Weather conditions (e.g., sunny, cloudy, rainy)</li>
    </ul>
  </li>
</ul>

<h3>2. Search Weather by Location</h3>
<ul>
  <li><b>Location List:</b> Provides a preloaded list of districts (zilla list) for easy location search.</li>
  <li><b>Search Functionality:</b> Allows users to search for weather details by district name.
    <ul>
      <li>Real-time search to narrow down district names as the user types.</li>
    </ul>
  </li>
</ul>

<h3>3. Backstack Navigation</h3>
<ul>
  <li>Efficient backstack management:</li>
  <li>Users can navigate back to the main weather screen from the search screen.</li>
  <li>Preserves the selected location and displays the weather for that location.</li>
</ul>
   <h3>Configuration Change Management</h3>
    <ul>
      <li>Handles configuration changes, such as screen rotation, without losing data or state.</li>
      <li>Uses <b>ViewModel</b> to retain UI-related data across configuration changes.</li>
      <li>Leverages <b>Jetpack Compose's</b> declarative UI to automatically recompose views as needed.</li>
    </ul>

 

<h2>🚀 Technologies Used</h2>
<p>This app is built using modern Android development tools and practices:</p>
<ul>
  <li><b>Jetpack Compose:</b> For building declarative and responsive UI.</li>
  <li><b>ViewModel:</b> To manage UI-related data in a lifecycle-aware way.</li>
  <li><b>MVVM Architecture:</b> For separating concerns and ensuring maintainability.</li>
  <li><b>Koin:</b> A lightweight dependency injection framework for managing app dependencies.</li>
  <li><b>Ktor:</b> For making efficient and structured API calls.</li>
  <li><b>Coroutines:</b> For managing asynchronous tasks smoothly and ensuring responsiveness.</li>
</ul>
<h2>🌐 API Usage Instructions</h2>

<h3>Step 1: Get an API Key</h3>
<ol>
  <li>Visit the website of the weather API provider (e.g., <a href="https://openweathermap.org/api" target="_blank">OpenWeatherMap</a>).</li>
  <li>Sign up for an account and obtain your API key.</li>
  <li>Keep the API key secure as it is required to make API requests.</li>
</ol>

<h3>Step 2: Configure the API Key and Base URL</h3>
<ol>
  <li>Open the <code>local.properties</code> file in your project (located in the root directory).</li>
  <li>Add the following entries:</li>
</ol>

<pre><code>
weather_api_key=your_api_key_here
base_url=https://api.openweathermap.org/data/2.5/
</code></pre>

<ol start="3">
  <li>Replace <code>your_api_key_here</code> with the API key you obtained.</li>
  <li>Clean and build the project.</li>
</ol>

<h2>📷 Screenshots</h2>
<p>
  <img src="https://raw.githubusercontent.com/mdmasum-shuvo/weather-api-jetpack-compose/main/ss/Screenshot_20241202-005111-portrait.png" width="30%" height="30%">
  <img src="https://raw.githubusercontent.com/mdmasum-shuvo/weather-api-jetpack-compose/main/ss/Screenshot_20241202-005217-landscape.png" width="80%" height="30%">
</p>
<p>
  <img src="https://raw.githubusercontent.com/mdmasum-shuvo/weather-api-jetpack-compose/main/ss/Screenshot_20241202-005147-portrait.png" width="30%" height="30%">
  <img src="https://raw.githubusercontent.com/mdmasum-shuvo/weather-api-jetpack-compose/main/ss/Screenshot_20241202-005202-landscape.png" width="80%" height="30%">
</p>



