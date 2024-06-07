import {addLike, dislike, addComment, viewComments} from "./actions.js";

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

async function handleViewComments(postId) {
    await viewComments(postId);
    window.window.href = '/'
}

window.handleAddLike = handleAddLike;
window.handleRemoveLike = handleRemoveLike;
window.handleAddComment = handleAddComment;
window.handleViewComments = handleViewComments;