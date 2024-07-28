import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

import ListEmployeeComponent from './components/ListEmployeeComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import {BrowserRouter,Routes,Route} from "react-router-dom"
import EmployeeComponent from './components/EmployeeComponent'
// Perform routing operations according to the entered Url
function App() {


  return (
    <>
      <BrowserRouter>
        <HeaderComponent />

          <Routes>
            {/* http://localhost:3000 */}
            <Route path="/" element={<ListEmployeeComponent />}  />  

            {/* http://localhost:3000/employess  */}
            <Route path="/employees" element={<ListEmployeeComponent />} />

            {/* http://localhost:3000/add-employee */}
            <Route path='/add-employee' element={<EmployeeComponent />} />

             {/* http://localhost:3000/edit-employee with dynamic ID (:id) */}
             <Route path='edit-employee/:id' element={<EmployeeComponent />}> </Route>

             {/* http://localhost:3000/view-employee with dynamic ID (:id) */}

             <Route path='/view-employee/:id' element={<EmployeeComponent />}> </Route>

          </Routes>
        <FooterComponent />
      </BrowserRouter>
   

    </>
  )
}

export default App
