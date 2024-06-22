import {addComment, viewComments} from "../actions/commentActions.js";
import {getContextPath} from "../utils.js";

async function handleAddComment(postId) {
    await addComment(postId);
    window.window.href = `${getContextPath()}/`;
}

async function handleViewComments(postId) {
    await viewComments(postId);
    window.window.href = `${getContextPath()}/`;
}

window.handleAddComment = handleAddComment;
window.handleViewComments = handleViewComments;