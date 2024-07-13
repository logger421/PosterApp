import {addLike, dislike} from "../actions/postActions.js";

async function handleAddLike(postId) {
    await addLike(postId);
    window.window.href = `/`;
}

async function handleRemoveLike(postId) {
    await dislike(postId);
    window.window.href = `/`;
}

window.handleAddLike = handleAddLike;
window.handleRemoveLike = handleRemoveLike;