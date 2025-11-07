// 로그아웃
const logout = document.getElementById("logout");
logout.addEventListener("click",async (e)=>{
    e.preventDefault();
    console.log("로그아웃 누름");
    await logoutService.logout();

    location.href='/admin/login';
});