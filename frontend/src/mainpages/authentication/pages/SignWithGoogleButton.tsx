import { jwtDecode } from "jwt-decode";
import { useEffect } from "react";
function SignWithGoogleButton() {
  const handleGoogleSigninResponse = (response: any) => {
    console.log("Encoded JWT:", response.credential);
    console.log(jwtDecode(response.credential));
  };

  const initGoogleSignIn = () => {
    google.accounts.id.initialize({
      client_id:
        "3887090815-827fb8ud2gr5bjstub7ar53na6t103ke.apps.googleusercontent.com",
      callback: handleGoogleSigninResponse,
    });

    google.accounts.id.renderButton(document.getElementById("gSignDiv")!, {
      theme: "filled_blue",
      size: "large",
      shape: "rectangular",
      type: "icon",
    });
  };
  useEffect(() => {
    initGoogleSignIn();
  }, []);
  return (
    <div id="gSignDiv"></div>
  );
}

export default SignWithGoogleButton;
