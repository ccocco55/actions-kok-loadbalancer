// const profileRegisterService = (() => {
//     const update = async (jsonData) => {
//         try {
//             const response = await fetch("/api/enterprise-console/profile/update", {
//                 method: "POST",
//                 headers: { "Content-Type": "application/json" },
//                 body: JSON.stringify(jsonData)
//             });
//
//             if (response.ok) {
//                 alert("프로필이 성공적으로 수정되었습니다.");
//                 return true; // 성공
//             } else {
//                 alert("수정 중 오류가 발생했습니다.");
//                 return false;
//             }
//         } catch (error) {
//             console.error(error);
//             alert("서버 통신 오류");
//             return false;
//         }
//     };
//
//     return { update };
// })();
