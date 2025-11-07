const headerService = (()=>{
    const logout = async () => {
        const response = await fetch('/api/auth/logout', {
            method: 'POST',
        });
        console.log("서비스 들어옴");
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Fetch error");
        }
    };
    const getRequestExperience = async (callback,experienceId='')=>{
        console.log(experienceId);
        const response = await fetch(`/api/main/requestExperience?experienceId=${experienceId}`);
        const experienceListDTO = await response.json();
        console.log(experienceListDTO.toString());
        if(callback){
            return callback(experienceListDTO);
        }
        return experienceListDTO;
    }
    const getRequestIntern = async (callback,internId='')=>{
            console.log("체험 서비스 들어옴");
            const response = await fetch(`/api/main/requestIntern?internId=${internId}`);
            const internListDTO = await response.json();
            console.log(internListDTO);
            if(internListDTO){
                return callback(internListDTO);
            }
            return internListDTO;
        }


    return {logout:logout,getRequestExperience:getRequestExperience,getRequestIntern:getRequestIntern}
})();