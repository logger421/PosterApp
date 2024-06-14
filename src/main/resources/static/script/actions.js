import {handleError, resolveCSRFToken, setEventListener} from "./utils.js";

export async function addLike(id) {
    const response = await fetch(`${window.location.origin}/api/posts/like/${id}`, {
        method: 'POST',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    })
    await handleError(response)
}

export async function dislike(id) {
    const response = await fetch(`${window.location.origin}/api/posts/dislike/${id}`, {
        method: 'POST',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    })
    await handleError(response)
}

export async function addComment(id) {
    let comment = document.getElementById('comment_v_' + id).value;
    document.getElementById('comment_v_' + id).value = "";
    if (!comment) {
        alert("Comment field must be filled out");
        return;
    }
    const response = await fetch(`${window.location.origin}/api/posts/addComment`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': resolveCSRFToken().token
        },
        body: JSON.stringify({
            postId: id,
            comment: comment
        })
    })

    if (!await handleError(response)) {
        window.location.reload()
    }
}

export async function viewComments(id) {
    const response = await fetch(`${window.location.origin}/api/posts/view/comments/${id}`, {
        method: 'GET'
    })
    const commentsBody = await response.json();
    let comments = document.getElementById('comments_' + id);

    comments.innerHTML = '';

    for (let i in commentsBody) {
        const comment = commentsBody[i];
        comments.innerHTML += `
            <div class="card m-1">
                <div class="card-title m-1"><b>Author: ${comment['userName']}</b></div>
                <div class="card-subtitle m-1"><b>Created: </b><em>${comment['createdAt']}</em></div>
                <div class="card-body m-1">${comment['comment']}</div>
            </div>
        `;
    }

    await handleError(response);
}

export async function editProfile() {
    let userName = document.getElementById('userName').value;
    let userEmail = document.getElementById('email').value;
    let userFirstName = document.getElementById('firstName').value;
    let userLastName = document.getElementById('lastName').value;

    const userDataResponse = await fetch(`${window.location.origin}/api/profile/get`, {
        method: 'GET',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    })

    const userData = await userDataResponse.json()

    userData.userName = userName;
    userData.email = userEmail;
    userData.firstName = userFirstName;
    userData.lastName = userLastName;

    const response = await fetch(`${window.location.origin}/api/profile/edit`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': resolveCSRFToken().token
        },
        body: JSON.stringify(userData)
    })

    if (!await handleError(response)) {
        window.location.reload()
    }
}

export function previewImageFile() {
    const imageUpload = document.getElementById('formImageFile');
    const file = imageUpload.files[0];
    const reader = new FileReader();
    reader.onload = function (e) {
        console.log(`Event triggered: ${e.target.result}`)
        const imageDataUrl = e.target.result;
        const imagePreview = document.getElementById('uploadImagePreview');
        if (imagePreview) {
            imagePreview.src = imageDataUrl;
        } else {
            const imagePreviewReal = document.getElementById('uploadImagePreviewReal');
            imagePreviewReal.src = imageDataUrl;
        }
    };
    reader.readAsDataURL(file);
}

export async function addFriend(userName) {
    const response = await fetch(`${window.location.origin}/friends/add?userName=${userName}`, {
        method: 'POST',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    })

    await handleError(response)
}