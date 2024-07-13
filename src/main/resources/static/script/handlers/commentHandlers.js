import {addComment, viewComments} from "../actions/commentActions.js";

async function handleAddComment(postId) {
    await addComment(postId);
    window.window.href = `/`;
}

async function handleViewComments(postId) {
    await viewComments(postId);
    window.window.href = `/`;
}

window.handleAddComment = handleAddComment;
window.handleViewComments = handleViewComments;