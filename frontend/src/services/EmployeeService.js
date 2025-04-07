import axios from "axios";
// Amazon frond end deployed Url
//  const REST_API_BASE_URL = 'http://15.206.27.104:8080/api/employees';

// const REST_API_BASE_URL = 'http://localhost:8080/api/employees';

const REST_API_BASE_URL = '/api/employees';

 export const listEmployees = () =>{
    return axios.get(REST_API_BASE_URL);
    // Return holds the promise,got by axios.get(url)
    // promise holds Response -->DataTransfer,error..
 }

export const createEmployee = (employee) => axios.post(REST_API_BASE_URL,employee);

export const getEmployee = (employeeId) =>axios.get(REST_API_BASE_URL +'/'+ employeeId);

export const updateEmployee = (employeeId,employee) =>axios.put(REST_API_BASE_URL +'/'+ employeeId,employee);

export const removeEmployee = (employeeId) => axios.delete(REST_API_BASE_URL + '/' + employeeId);
