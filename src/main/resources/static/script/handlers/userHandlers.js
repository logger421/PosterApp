import {addFriend, deleteFriend, editProfile} from "../actions/userActions.js";
import {getContextPath} from "../utils.js";

async function handleAddFriend(userName) {
    await addFriend(userName)
    window.location.href = `${getContextPath()}/user/friends?friendAdded`
}

async function handleDeleteFriend(userName) {
    await deleteFriend(userName)
    window.location.href = `${getContextPath()}/user/friends?friendDeleted`
}

async function handleEditProfile() {
    await editProfile()
    window.window.href = `${getContextPath()}/dashboard`;
}

window.handleEditProfile = handleEditProfile;
window.handleAddFriend = handleAddFriend;
window.handleDeleteFriend = handleDeleteFriend;