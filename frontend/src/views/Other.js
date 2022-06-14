import React, { useState } from 'react';
import { Alert, Button, Card } from 'flowbite-react';
import { FaInfoCircle } from 'react-icons/fa';
import { useAuth0, withAuthenticationRequired } from "@auth0/auth0-react";

const OtherView = () => {
    const apiOrigin = "http://localhost:8989";
    const [state, setState] = useState({
        showResult: false,
        endpointMessage: "",
        error: null
    });

    const {
        loginWithPopup,
        getAccessTokenWithPopup,
        getAccessTokenSilently
    } = useAuth0();

    const handleConsent = async () => {
        try {
            await getAccessTokenWithPopup();
            setState({
                ...state,
                error: null
            });
        } catch (error) {
            setState({
                ...state,
                error: error.error
            });
        }
    };

    const handleLoginAgain = async () => {
        try {
            await loginWithPopup();
            setState({
                ...state,
                error: null
            });
        } catch (error) {
            setState({
                ...state,
                error: error.error
            });
        }

        await callPublicEndpoint();
    };

    const callProtectedEndpoint = async () => {
        try {
            const token = await getAccessTokenSilently();
            console.log("Token", token);
            const response = await fetch(`${apiOrigin}/profiles`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log("Private response", response);
            const responseData = await response.json();
            setState({
                ...state,
                showResult: true,
                endpointMessage: responseData
            });
        } catch (error) {
            setState({
                ...state,
                error: error.error
            });
        }
    };

    const callPublicEndpoint = async () => {
        try {
            const response = await fetch(`${apiOrigin}/actuator/health`, {
                method: 'GET',
                mode: 'cors',
                credentials: 'same-origin'
            });
            console.log("Public resposne", response)
            const responseData = await response.json();
            setState({
                ...state,
                showResult: true,
                endpointMessage: responseData
            });
        } catch (error) {
            setState({
                ...state,
                error: error.error
            });
        }
    };

    const handle = (e, fn) => {
        e.preventDefault();
        fn();
    };

    return (
        <>
            <div className="mb-5">
                {state.error === "consent_required" && (
                    <Alert color="warning" icon={FaInfoCircle}>
                        You need to{" "}
                        <a
                            href="#/"
                            className="alert-link"
                            onClick={(e) => handle(e, handleConsent)}
                        >
                            consent to get access to users api
                        </a>
                    </Alert>
                )}
                {state.error === "login_required" && (
                    <Alert color="warning" icon={FaInfoCircle}>
                        You need to{" "}
                        <a
                            href="#/"
                            className="alert-link"
                            onClick={(e) => handle(e, handleLoginAgain)}
                        >
                            log in again
                        </a>
                    </Alert>
                )}
                <Card className='text-white mt-10'>
                    <h1 className='text-2xl font-bold'>External API</h1>
                    <p>
                        Ping an external API by clicking one of the buttons below. The private
                        APIs will call the external API using an access token, and the API
                        will validate it using the API's audience value.
                    </p>
                </Card>
                <div className='flex mt-5'>
                    <Button gradientDuoTone="greenToBlue" className='mx-10' onClick={callPublicEndpoint}>Ping Public Endpoint</Button>
                    <Button gradientDuoTone="pinkToOrange" onClick={callProtectedEndpoint}>Ping Protected Endpoint</Button>
                </div>


            </div>

            <div className="result-block-container">
                {state.showResult && (
                    <div className="result-block" data-testid="api-result">
                        <h6 className="muted">Result</h6>
                        {state.endpointMessage.msg}
                    </div>
                )}
            </div>
        </>
    )
}

export default withAuthenticationRequired(OtherView);
