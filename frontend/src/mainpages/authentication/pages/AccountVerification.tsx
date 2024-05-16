import axios from "axios";
import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom";
import { EMAIL_VERIFICATION_URL } from "../../../systemutils/SystemConstants";

function AccountVerification() {
  const navigate = useNavigate();
  const [text,setText] = useState<string>("Processing....");
  const { token } = useParams();
  const [disable,setDisable] = useState(false);
  useEffect(() => {
    if(!disable){
      axios.get(EMAIL_VERIFICATION_URL+token)
      .then((response)=>{
        if(response.data.status){
          navigate("/");
          setText("Verified")
  
        }
      })
      .catch(()=>{
        setText("failed")
      })
    }
    setDisable(true);
  }, []);
  return (
    <>

        <div className="w-[100%] h-[100dvh] bg-secondary flex justify-center items-center">
            <div className="">

                <span className="text-white text-4xl font-semibold">{text}</span>

            </div>
        </div>
    
    </>
  )
}

export default AccountVerification
