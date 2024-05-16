import { AuthResponse, AuthenticationService } from "./AuthenticationService";

export async function LoginService(email:string,password:string) : Promise<AuthResponse>{
  const validationResponse:AuthResponse = AuthenticationService("login",email,password,"");
  if(validationResponse.isStatus){
    
  }
  return validationResponse;
}
