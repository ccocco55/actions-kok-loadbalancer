const adminService = (()=>{
        const login = async (user) => {
            const response = await fetch('/api/auth/login', {
                method: 'POST',
                body: JSON.stringify(user),
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                location.href='/admin/login?error'
                const errorText = await response.text();
                throw new Error(errorText || "Fetch error");

            }

            return await response.json();
        }

        return {login:login}
})();