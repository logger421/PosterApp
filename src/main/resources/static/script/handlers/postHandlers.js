import {addLike, dislike} from "../actions/postActions.js";
import {getContextPath} from "../utils.js";

async function handleAddLike(postId) {
    await addLike(postId);
    window.window.href = `${getContextPath()}/`;
}

async function handleRemoveLike(postId) {
    await dislike(postId);
    window.window.href = `${getContextPath()}/`;
}

window.handleAddLike = handleAddLike;
window.handleRemoveLike = handleRemoveLike;