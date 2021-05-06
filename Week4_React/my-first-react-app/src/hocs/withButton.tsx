import React from 'react';

interface WithButtonProps {
  onClick: (event: any) => void;
}
const withButton = (WrappedComponent: any) => {
  class Hoc extends React.Component<WithButtonProps> {
    render() {
      const { onClick, ...props } = this.props;
      return (
        <button onClick={onClick}>
          <WrappedComponent {...props} />
        </button>
      );
    }
  }

  return Hoc;
};

export default withButton;
