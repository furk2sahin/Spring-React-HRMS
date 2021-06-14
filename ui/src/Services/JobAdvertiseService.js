import { instance } from "./instance/Instance"

export const getJobAdvertisesByActiveTrue = () => {
    return instance.get("/job-advertise/findAllByActiveTrue")
}

export const createJobAdvertise = (jobAdvertise, expiryDays) => {
    return instance.post(`/job-advertise/add/${expiryDays}`, jobAdvertise)
}

export const getJobAdvertisesByActiveTrueSorted = (sortId) => {
    return instance.get(`/job-advertise/findAllSorted/${sortId}`)
}

export const getJobAdvertisesByEmployerUuid = (uuid) => {
    return instance.get(`/job-advertise/findAllByEmployerUuid/${uuid}`)
}

export const getJobAdvertisesByCityId = (id) => {
    return instance.get(`/job-advertise/findAllByCityId/${id}`)
}

export const getJobAdvertisesByJobPositionId = (id) => {
    return instance.get(`/job-advertise/findAllByJobPositionId/${id}`)
}

export const getJobAdvertisesByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase = (name) => {
    return instance.get(`/job-advertise/findAllByCompanyNameContains/${name}`)
}

export const getJobAdvertisesByActiveTruePaged = (pageNumber, pageSize) => {
    return instance.get(`/job-advertise/findAllByActiveTruePaged/${pageNumber}/${pageSize}`)
}

export const updateJobAdvertiseStatus = (id, active) => {
    return instance.put(`/job-advertise/updateStatus/${id}/${active}`)
}