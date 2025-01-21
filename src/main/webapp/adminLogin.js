
console.log("called admin login js");
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    // Static credentials for admin login
    const validUsername = 'admin';
    const validPassword = 'password';
  
    if (username === validUsername && password === validPassword) {
//        console.log("validated");
    	// Redirect to home page if credentials match
        window.location.href = 'AdminHome.jsp';
    } else {
        // Show error message
    	console.log("not validaetd");
        document.getElementById('errorMessage').style.display = 'block';
    }
  });
  