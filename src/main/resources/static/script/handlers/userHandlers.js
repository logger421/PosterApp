import {addFriend, deleteFriend, editProfile} from "../actions/userActions.js";

async function handleAddFriend(userName) {
    await addFriend(userName)
    window.location.href = `/user/friends?friendAdded`
}

async function handleDeleteFriend(userName) {
    await deleteFriend(userName)
    window.location.href = `/user/friends?friendDeleted`
}

async function handleEditProfile() {
    await editProfile()
    window.window.href = `/dashboard`;
}

window.handleEditProfile = handleEditProfile;
window.handleAddFriend = handleAddFriend;
window.handleDeleteFriend = handleDeleteFriend;