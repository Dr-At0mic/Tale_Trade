import axios from "axios";
import { AuthResponse, AuthenticationService } from "./AuthenticationService";
import { USER_REGISTER_URL } from "../../../systemutils/SystemConstants";

interface signupRequest {
  emailAddress: string;
  password: string;
}

export async function SignUpService(
  email: string,
  password: string,
  confirmPassword: string
): Promise<AuthResponse> {
  const validationResponse: AuthResponse = AuthenticationService(
    "signup",
    email,
    password,
    confirmPassword
  );
  if (!validationResponse.isStatus) return validationResponse;

  const body: signupRequest = {
    emailAddress: email,
    password: password,
  };
  try {
    const serverResponse = await axios.post(USER_REGISTER_URL, body);
    return {
      isStatus: true,
      message: serverResponse.data.message,
    };
  } catch (error) {
    if (
      axios.isAxiosError(error) &&
      error.response?.data.errorCode === "1003"
    ) {
      return {
        isStatus: false,
        message: "Email Already Exist",
      };
    } else {
      return {
        isStatus: false,
        message: "Server not Reachable",
      };
    }
  }
}
