import { Navbar, Dropdown, Avatar, Button } from 'flowbite-react'
import { useAuth0 } from "@auth0/auth0-react";
import { Link } from 'react-router-dom';

const Nav = () => {
    const { loginWithRedirect, logout, user, isAuthenticated, isLoading } = useAuth0();
    return (
        <Navbar
            fluid={true}
        >
            <Link to="/">
                <Navbar.Brand>
                    <img
                        src="logo192.png"
                        className="mr-3 h-6 sm:h-9"
                        alt="GobbleDN Logo"
                    />
                    <span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white">
                        GobbleDN
                    </span>
                </Navbar.Brand>
            </Link>
            <div className="flex md:order-2">
                {isAuthenticated ?
                    <Dropdown
                        arrowIcon={false}
                        inline={true}
                        label={<Avatar alt={user.nickname} img={user.picture} rounded={true} />}
                    >
                        <Dropdown.Header>
                            <span className="block text-sm">
                                {user.nickname}
                            </span>
                            <span className="block truncate text-sm font-medium">
                                {user.email}
                            </span>
                        </Dropdown.Header>
                        <Dropdown.Item>
                            Profile
                        </Dropdown.Item>
                        <Dropdown.Item>
                            Settings
                        </Dropdown.Item>
                        <Dropdown.Divider />
                        <Dropdown.Item onClick={() => logout({ returnTo: window.location.origin })}>
                            Sign out
                        </Dropdown.Item>
                    </Dropdown> :
                    <div className='flex'>
                        <Button
                            outline={true}
                            gradientDuoTone="greenToBlue"
                            onClick={() => loginWithRedirect()}
                            className="mr-4"
                        >
                            Log In
                        </Button>
                        <Button
                            outline={true}
                            gradientDuoTone="pinkToOrange"
                            onClick={() => loginWithRedirect({ screen_hint: 'signup' })}
                        >
                            Sign Up
                        </Button>
                    </div>}
                <Navbar.Toggle />
            </div>
            <Navbar.Collapse>
                <Link to="/">
                    <Navbar.Link>
                        Home
                    </Navbar.Link>
                </Link>
                <Link to="/explore">
                    <Navbar.Link>
                        Explore
                    </Navbar.Link>
                </Link>
                <Link to="/notifications">
                    <Navbar.Link>
                        Notifications
                    </Navbar.Link>
                </Link>
                <Link to="/messages">
                    <Navbar.Link>
                        Messages
                    </Navbar.Link>
                </Link>
            </Navbar.Collapse>
        </Navbar>
    )
}

export default Nav;