import React, { useEffect, useState } from 'react'
import { listEmployees, removeEmployee } from '../services/EmployeeService'
import { useNavigate } from 'react-router-dom'

// List EmployeeComponent will be rendered as default or web application first page
const ListEmployeeComponent = () => {
    
    const [employees,setEmployees]=useState([]);
    
    useEffect(
        () => {
            getAllEmployees();
            },[]
            )

    const navigator= useNavigate(); 

    function getAllEmployees(){
        listEmployees().then(
            (response)  =>
                {
                 setEmployees(response.data);
                           }
                           )
                           .catch(
                                error =>
                                    {
                                    console.error(error)
                                    }
                                 )
                               
    }
    
    function addNewEmployee() {
        navigator("/add-employee");
    }  
    
   
    function updateEmployee(id){
        navigator(`/edit-employee/${id}`)
    }

    function deleteEmployee(id)
    {
        console.log(id);
        removeEmployee(id).then( () => {
            getAllEmployees();
        }).catch(error => console.error(error));

    }
    function viewEmployee(id){
        navigator(`/view-employee/${id}`);
    }


  return (
    <div className='container'>
     <h3 className='text-center'>Employees List</h3>
     <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
     <table className='table table-striped table-bordered'>
        <thead>
            <tr >
                <th>Employee Id</th>
                <th>Employee First Name</th>
                <th>Employee Last Name</th>
                <th>Employee Email ID</th>
                <th>Actions</th>
            </tr>

        </thead>
        <tbody>
            {/* conditional rendering */}
             
            {

                employees.map((employee) => (
                    <tr key={employee.id}>
                        <td>{employee.id}</td>
                        <td>{employee.firstName}</td>
                        <td>{employee.lastName}</td>
                        <td>{employee.email}</td>
                        <td>
                            <button className='btn btn-info' onClick={()=>updateEmployee(employee.id)}>Update</button>
                            <button className='btn btn-danger ms-3' onClick={()=>deleteEmployee(employee.id)}>Delete</button>
                            <button className='btn btn-info ms-3' onClick={()=>viewEmployee(employee.id)}>View</button>
                        </td>
                    </tr>
                )
               )
            }
            
        </tbody>
     </table>
    </div>
  )
}

export default ListEmployeeComponent

// const dummyData=[
    //     {
    //         "id":1,
    //         "firstName":"Arun",
    //         "lastName":"Kumar",
    //         "email":"arun@gmail.com"
    //     },
    //     {
    //         "id":2,
    //         "firstName":"Tom",
    //         "lastName":"cruise",
    //         "email":"tom@gmail.com"

    //     },
    //     {
    //         "id":103,
    //         "firstName":"APJ",
    //         "lastName":"Kalam",
    //         "email":"apj@gmail.com"

    //     }]