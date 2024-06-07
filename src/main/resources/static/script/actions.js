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