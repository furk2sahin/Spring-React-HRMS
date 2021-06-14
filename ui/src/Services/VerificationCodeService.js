import { instance } from "./instance/Instance"

export const confirmVerificationCode = (uuid, code) => {
    return instance.post(`/verification-code/confirm/${uuid}/${code}`)
}