import {addLike, dislike, addComment} from "./actions.js";

async function handleAddLike(postId) {
    await addLike(postId);
    window.window.href = '/'
}

async function handleRemoveLike(postId) {
    await dislike(postId);
    window.window.href = '/'
}

async function handleAddComment(postId) {
    await addComment(postId);
    window.window.href = '/'
}

window.handleAddLike = handleAddLike;
window.handleRemoveLike = handleRemoveLike;
window.handleAddComment = handleAddComment;