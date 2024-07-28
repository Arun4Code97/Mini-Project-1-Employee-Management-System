import React from 'react'
//Keeping header component constantly in ListEmployeeComponent
const HeaderComponent = () => {
  return (
    <div>
      <header>
        <nav className='navbar navbar-dark bg-dark ps-2'>
            <a className='navbar-brand' href="#">Employee Management System</a>
        </nav>
      </header>
    </div>
  )
}

export default HeaderComponent
