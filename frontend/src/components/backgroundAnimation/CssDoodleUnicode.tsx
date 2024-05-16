import  'css-doodle';
function CssDoodleUnicode() {
  return (
    <css-doodle>
    {`
     @grid: 8 / 100vmax / #000;
     @content: @unicode.r(0x2500, 0x150f);
     color: hsla(@r360, 100%, 50%, @r.9);
     font-size: 10vmin;
     font-family: sans-serif;
  `}
  </css-doodle>
  )
}

export default CssDoodleUnicode