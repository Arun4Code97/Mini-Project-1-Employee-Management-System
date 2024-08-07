import React, { useState,useEffect } from 'react'
import { createEmployee, getEmployee, updateEmployee } from '../services/EmployeeService';
import { useNavigate,useParams,useLocation } from 'react-router-dom';
// will be rendered this same Employeecomponent for adding/viewing/updating purpose
const EmployeeComponent = () => {
  
    const [firstName,setFirstName]=useState('');
    const [lastName,setLastName]=useState('');
    const [email,setEmail]=useState('');

    const [errors,setErrors] = useState({
        firstName: '',
        lastName: '',
        email:''
                });
   

    const navigator = useNavigate();

    const {id}=useParams();
    const employeeId = id || ''; 

    const currentLocation= useLocation();
    const currentpath =currentLocation.pathname;   // currentpath === `/view-employee/${employeeId}`
    const viewEmployee = currentpath.includes("/view-employee/");  

    //Changing page employee card header details according to the operation performed by user.
    function pageTitle(){
                            if( viewEmployee )
                            return  <h2 className='text-center'>  Employee details</h2>; 

                            else if(employeeId)
                            return  <h2 className='text-center'> Update Employee</h2>;

                             else
                             return  <h2 className='text-center'> Add Employee</h2>;

                         }     

    useEffect(()=>{
        if(employeeId){
            getEmployee(employeeId).then((response)=>
                {
                    setFirstName(response.data.firstName);
                    setLastName(response.data.lastName);
                    setEmail(response.data.email);    
                }
                ).catch(error => console.error(error))}
                 },[id]);

    function handleFirstName(e) {
        setFirstName(e.target.value)
    }

    const handleLastName = (e) => setLastName(e.target.value);
    
 
    function saveOrUpdateEmployee(e) {

        e.preventDefault();

        if(validateForm())
                            {

        const employee ={firstName,lastName,email}
        console.log(employee);
        
        if(id)
            {
            updateEmployee(id,employee).then(
                (response) => {
                    console.log(response.data);
                    navigator("/employees");
                }
            ).catch(error => console.error(error));
           }
          else
           {
            createEmployee(employee).then(
                (response)=> {
                    console.log(response.data);
                    navigator("/employees");
                             }
                ).catch((error)=> {if (error.response && error.response.status === 302) { // 302  is the status code for found already (email already exists)
                    setErrors({ ...errors, email: 'Email ID already exists' });
                } else {
                    console.error(error);
                }
            });
           }
                             }
                             }     


//Validation for the form input fields

    function validateForm(){
        let valid = true;
        const errorsCopy = {...errors};

        if(firstName.trim()){
            errorsCopy.firstName = '';}
        else{
            errorsCopy.firstName ="First Name is required";
            valid = false;
            }
        
        if(lastName.trim()){   
            errorsCopy.lastName='';}
        else{
            errorsCopy.lastName ="Last name is required";
            valid=false;
            }     
        
        if(email.trim()){
                errorsCopy.email='';
                        }
        else{
            errorsCopy.email="Email is required ";
            valid=false;
            }     
            
        setErrors(errorsCopy);   
        
        return valid;
                            }
           
    

  return (
    <div className='container'>
        <div className='row'>
           <div className='card col-md-6 offset-md-3 mt-5' >
            {pageTitle()} 
            <div className='card-body'>
                <form>
                    <div className='form-group mb-2'>
                        <label className='form-label'>First Name:</label>
                        <input type='text' 
                           placeholder='Enter employee first name'
                           name='firstName'
                           value={firstName}
                           className={`form-control ${errors.firstName ? 'is-invalid' :''}`}
                           onChange={handleFirstName}
                           
                   
                        />
                        {errors.firstName && <div className='invalid-feedback'>{errors.firstName}</div>}     
                    </div>
                    
                    <div className='form-group mb-2'>
                        <label className='form-label'>Last Name:</label>
                        <input type='text' 
                           placeholder='Enter employee last name'
                           name='lastName'
                           value={lastName}
                           className={`form-control ${errors.lastName ? 'is-invalid' :''}`}
                           onChange={handleLastName}
                         
                        />
                        {errors.lastName && <div className='invalid-feedback'>{errors.lastName}</div>}         
                    </div>

                    <div className='form-group mb-2'>
                        <label className='form-label'>Email:</label>
                        <input type='text' 
                           placeholder='Enter employee email id'
                           name='email'
                           value={email}
                           className={`form-control ${errors.email ? 'is-invalid' :''}`}
                           onChange={ (e) => setEmail(e.target.value)}
                      
                        />    
                        {errors.email && <div className='invalid-feedback'>{errors.email}</div>}     
                    </div>    

                    { viewEmployee !== true && <button className='btn btn-success' onClick={saveOrUpdateEmployee}>Submit</button>}

                </form>
            </div>

           </div>
 
        </div>
    </div>
  )
}

export default EmployeeComponent
