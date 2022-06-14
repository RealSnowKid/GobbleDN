import React from 'react';
import { Card } from 'flowbite-react';

const HomeView = () => {
    return (
        <>
            <div className="max-w-2xl mt-5 mx-auto">
                <Card imgSrc="https://media1.popsugar-assets.com/files/thumbor/q_eu4G_Yfvd1qUU7rkJYpC9Qalk/0x532:1560x2092/fit-in/2048xorig/filters:format_auto-!!-:strip_icc-!!-/2019/11/18/102/n/1922729/2010a3325dd3450317e273.27544324_/i/healthy-meal-prep-dinner-recipes.jpg">
                    <h5 className="text-2xl font-bold tracking-tight text-gray-900 dark:text-white mx-auto">
                        GobbleDN
                    </h5>
                    <p className="text-2xl italic text-gray-500 dark:text-gray-400 mx-auto">
                        A social media for foodies.
                    </p>
                </Card>
            </div>
        </>
    )
}

export default HomeView;
