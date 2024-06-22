import {getWindowLocation, handleError, resolveCSRFToken} from "../utils.js";

export async function addLike(id) {
    const response = await fetch(`${getWindowLocation()}/api/posts/like/${id}`, {
        method: 'POST',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    })

    await handleError(response)
}

export async function dislike(id) {
    const response = await fetch(`${getWindowLocation()}/api/posts/dislike/${id}`, {
        method: 'POST',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    })

    await handleError(response)
}