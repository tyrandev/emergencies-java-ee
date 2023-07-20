function visuelNav(state, adminState) {
    let connectionState = state;
    let isAdmin = adminState;
    let btnLogout = document.getElementById("logout");
    let btnLogin = document.getElementById("login");
    let btnAdmin = document.getElementById("admin");
    let btnProfile = document.getElementById("profile");

    if (connectionState) {
        console.log(connectionState);
        if (isAdmin) {
            btnAdmin.style.display = "block";
        }
        console.log(isAdmin);
        btnProfile.style.display = "block";
        btnLogin.style.display = "none";
        btnLogout.style.display = "block";
    } else {
        console.log(connectionState);
        btnLogin.style.display = "block";
        btnLogout.style.display = "none";
        btnAdmin.style.display = "none";
        btnProfile.style.display = "none";
    }
}

function hideCreateLessonButton(adminUser) {
    let isAdmin = adminUser;
    let formLessonCreate = document.getElementById("formCreateLesson");
    if (isAdmin)
    {
        formLessonCreate.style.display = "block";
    }
    else
    {
        formLessonCreate.style.display = "none";
    }
}
