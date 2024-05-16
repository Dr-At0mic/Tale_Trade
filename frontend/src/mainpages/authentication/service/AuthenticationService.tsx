const validateEmail = (email: string): boolean => {
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return regex.test(email);
};

export interface AuthResponse {
  isStatus: boolean;
  message: string;
}
export function AuthenticationService(
  authType: string,
  email: string,
  password: string,
  confirmPassword: string
): AuthResponse {
  if (authType === "signup") {
    if (!email || !password || !confirmPassword)
      return {
        isStatus: false,
        message: "All fields are madatory",
      };
    if (!validateEmail(email))
      return {
        isStatus: false,
        message: "Enter a Valid Email Id",
      };
    if (password.length < 8)
      return {
        isStatus: false,
        message: "password must have atleast 8 characters",
      };
    if (password !== confirmPassword)
      return {
        isStatus: false,
        message: "password doesnt match confirm passowrd",
      };
  }
  if (authType === "login") {
    if (!email || !password)
      return {
        isStatus: false,
        message: "All fields are madatory",
      };
    if (!validateEmail(email))
      return {
        isStatus: false,
        message: "Enter a Valid Email Id",
      };
    if (password.length < 8)
      return {
        isStatus: false,
        message: "password must have atleast 8 characters",
      };
  }

  return {
    isStatus: true,
    message: "",
  };
}
