import {getWindowLocation, handleError, resolveCSRFToken} from "../utils.js";

export async function addFriend(userName) {
    const response = await fetch(`${getWindowLocation()}/user/friends/add?userName=${userName}`, {
        method: 'POST',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    })

    await handleError(response);
}

export async function deleteFriend(userName) {
    const response = await fetch(`${getWindowLocation()}/user/friends/delete?userName=${userName}`, {
        method: 'DELETE',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    })

    await handleError(response);
}

export async function editProfile() {
    let userName = document.getElementById('userName').value;
    let userEmail = document.getElementById('email').value;
    let userFirstName = document.getElementById('firstName').value;
    let userLastName = document.getElementById('lastName').value;

    const userDataResponse = await fetch(`${getWindowLocation()}/api/profile/get`, {
        method: 'GET',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    })

    const userData = await userDataResponse.json()

    userData.userName = userName;
    userData.email = userEmail;
    userData.firstName = userFirstName;
    userData.lastName = userLastName;

    const response = await fetch(`${getWindowLocation()}/api/profile/edit`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': resolveCSRFToken().token
        },
        body: JSON.stringify(userData)
    })

    await handleError(response);
}