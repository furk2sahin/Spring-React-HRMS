import { instance } from "./instance/Instance"

export const verifyEmployer = (employerUuid, personnelUuid) => {
    return instance.post(`/employer-verify/verify/${employerUuid}/${personnelUuid}`)
}