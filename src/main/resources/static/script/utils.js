export function resolveCSRFToken() {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    return {token: token, header: header};
}

export async function handleError(response) {
    if (response.status !== 200) {
        const text = await response.json();
        alert(text.message);
        return true;
    }
    return false;
}

export function setEventListener(objectId, eventListener) {
    const object = document.getElementById(objectId);
    object.addEventListener('click', eventListener);
}

export function getWindowLocation() {
    return window.location.origin;
}