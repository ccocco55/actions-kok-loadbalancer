const service = (()=>{
    const send= async (userDTO)=>{
        const response = await fetch("/sms/send",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userDTO)
        });
        return await response.json();
    }
    const getEmailByPhoneNumber = async (userDTO) => {
        const response = await fetch("/sms/find-email", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userDTO)
        });

        return await response.json();
    };
    return({send:send,getEmailByPhoneNumber:getEmailByPhoneNumber})
})();