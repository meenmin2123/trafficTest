import React, { useEffect } from 'react';
import AOS from 'aos';
import 'aos/dist/aos.css'; // AOS CSS 가져오기
import style from './ScrollDown.css';

function ScrollDown() {
    console.log("ScrollDown!!!");

    useEffect(() => {
        AOS.init({
            duration: 500, // 애니메이션 지속 시간
            easing: 'ease-in-out', // 애니메이션 가속도
            debug: true, // 디버깅 활성화
            delay: "0"
        });

        AOS.refresh();
    }, []);

    return (

            <div className={`chevron bottom registerbox`}>
                <a className={`registerTag`} href='/register'>가입하기</a>
            </div>

    );
}

export default ScrollDown;
