import {getWindowLocation, handleError, resolveCSRFToken} from "../utils.js";

export async function addComment(id) {
    let comment = document.getElementById('comment_v_' + id).value;
    document.getElementById('comment_v_' + id).value = "";
    if (!comment) {
        alert("Comment field must be filled out");
        return;
    }

    const response = await fetch(`${getWindowLocation()}/api/post/comments`, {
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
    const response = await fetch(`${getWindowLocation()}/api/post/comments/${id}`, {
        method: 'GET'
    })

    const commentsBody = await response.json();
    let comments = document.getElementById('comments_' + id);

    comments.innerHTML = '';

    for (let i in commentsBody) {
        const comment = commentsBody[i];
        const date = new Date(comment['createdAt']);
        comments.innerHTML += `
            <div class="card shadow-sm mt-2">
                <div class="card-body">
                    <div class="card-title">
                        <div class="row">
                            <div class="col"><b>${comment['userName']}</b></div>
                            <div class="col text-end"><em>${date.toDateString()}</em></div>
                        </div>
                        <div class="card-text mt-2">${comment['comment']}</div>
                    </div>
                </div>
            </div>
        `;
    }

    await handleError(response);
}

export async function deleteComment(id) {
}