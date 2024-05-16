interface probType{
  title: string
}
function SetTitle({title}:probType) {
  document.title = title;    
  return null;
}

export default SetTitle