const memberService = (()=>{
        const login = async (user) => {
            const response = await fetch('/api/auth/login', {
                method: 'POST',
                body: JSON.stringify(user),
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                location.href='/member/login?fail'

                const errorText = await response.text();
                throw new Error(errorText || "Fetch error");

            }

            return await response.json();
        }
        const reset = async ()=>{
            const response = fetch("/api/auth/reset-cookies",{
                method:'POST',
            })
            return await response.json();
        }

        return {login:login,reset:reset}
})();