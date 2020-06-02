
/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function addRandomCountry(){
    const countries = ['Argentina', 'Germany', 'Finland', 'Thailand', 'Botswana'];

    // Pick a random country
    const country = countries[Math.floor(Math.random() * countries.length)];

    // Add it to the page
    const countryContainer = document.getElementById('country-container')
    countryContainer.innerText = country;
}
