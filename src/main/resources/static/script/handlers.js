import {addLike, dislike, addComment, viewComments, previewImageFile, editProfile, addFriend} from "./actions.js";

async function handleAddLike(postId) {
    await addLike(postId);
    window.window.href = '/';
}

async function handleRemoveLike(postId) {
    await dislike(postId);
    window.window.href = '/';
}

async function handleAddComment(postId) {
    await addComment(postId);
    window.window.href = '/';
}

async function handleViewComments(postId) {
    await viewComments(postId);
    window.window.href = '/';
}

async function handleEditProfile() {
    await editProfile()
    window.window.href = '/dashboard';
}

function handleImagePreview() {
    previewImageFile();
}

async function handleAddFriend(userName) {
    await addFriend(userName)
}

window.handleAddLike = handleAddLike;
window.handleRemoveLike = handleRemoveLike;
window.handleAddComment = handleAddComment;
window.handleViewComments = handleViewComments;
window.handleImagePreview = handleImagePreview;
window.handleEditProfile = handleEditProfile;
window.handleAddFriend = handleAddFriend;