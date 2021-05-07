import { useEffect, useState } from 'react';

export default function Fetching(props: { id: number }) {
  const [user, setUser] = useState(null);

  async function fetchUserData(id: any) {
    const response = await fetch('/', id);
    setUser(await response.json());
  }

  useEffect(() => {
    fetchUserData(props.id);
  }, [props.id]);

  if (user) {
    return (
      <div>
        <span>{user.name}</span>
        <span>{user.age}</span>
      </div>
    );
  } else {
    return null;
  }
}
